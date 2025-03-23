FROM amazoncorretto:17-alpine

USER root

ARG GRADLE_VERSION=8.7
ARG ORAS_VERSION=1.2.2

# Install dev tools and Docker CLI
RUN apk add --no-cache \
    curl \
    git \
    bash \
    jq \
    yq \
    less \
    unzip \
    shadow \
    docker-cli \
    openssh-client \
    && addgroup -S docker && adduser -S docker -G docker

# Install Gradle
RUN curl -sSL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && \
    unzip -q gradle.zip -d /opt/gradle && \
    rm gradle.zip

ENV GRADLE_HOME=/opt/gradle/gradle-${GRADLE_VERSION}
ENV PATH="${GRADLE_HOME}/bin:$PATH"

# Install ORAS
RUN curl -LOs https://github.com/oras-project/oras/releases/download/v${ORAS_VERSION}/oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    tar -xzf oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    mv oras /usr/local/bin/oras && \
    chmod +x /usr/local/bin/oras && \
    rm -f oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    oras version

WORKDIR /workspace
