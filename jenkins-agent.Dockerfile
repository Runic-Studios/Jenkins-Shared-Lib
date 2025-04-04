FROM eclipse-temurin:21

USER root

ARG GRADLE_VERSION=8.7
ARG ORAS_VERSION=1.2.2

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    git \
    bash \
    jq \
    less \
    unzip \
    passwd \
    docker.io \
    openssh-client \
    && rm -rf /var/lib/apt/lists/*

#RUN apt-get update && apt-get install -y software-properties-common && add-apt-repository ppa:rmescandon/yq && apt update && apt install -y yq

RUN (type -p wget >/dev/null || (apt-get update && apt-get install wget -y)) \
	&& mkdir -p -m 755 /etc/apt/keyrings \
        && out=$(mktemp) && wget -nv -O$out https://cli.github.com/packages/githubcli-archive-keyring.gpg \
        && cat $out | tee /etc/apt/keyrings/githubcli-archive-keyring.gpg > /dev/null \
	&& chmod go+r /etc/apt/keyrings/githubcli-archive-keyring.gpg \
	&& echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | tee /etc/apt/sources.list.d/github-cli.list > /dev/null \
	&& apt-get update \
	&& apt-get install gh -y

RUN curl -sLo /usr/local/bin/yq https://github.com/mikefarah/yq/releases/latest/download/yq_linux_amd64 && \
    chmod +x /usr/local/bin/yq

RUN curl -sSL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && \
    unzip -q gradle.zip -d /opt/gradle && \
    rm gradle.zip

ENV GRADLE_HOME=/opt/gradle/gradle-${GRADLE_VERSION}
ENV PATH="${GRADLE_HOME}/bin:$PATH"

RUN curl -LOs https://github.com/oras-project/oras/releases/download/v${ORAS_VERSION}/oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    tar -xzf oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    mv oras /usr/local/bin/oras && \
    chmod +x /usr/local/bin/oras && \
    rm -f oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    oras version

WORKDIR /workspace
