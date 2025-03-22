FROM docker:24.0.6

USER root

ARG ORAS_VERSION=1.2.2

RUN apk add --no-cache \
    git \
    curl \
    bash \
    jq \
    yq \
    github-cli \
    less

RUN apk add --no-cache curl tar && \
    curl -LOs https://github.com/oras-project/oras/releases/download/v${ORAS_VERSION}/oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    tar -xvzf oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    mv oras /usr/local/bin/oras && \
    chmod +x /usr/local/bin/oras && \
    rm -f oras_${ORAS_VERSION}_linux_amd64.tar.gz && \
    oras version