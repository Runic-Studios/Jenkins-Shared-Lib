FROM docker:24.0.6

USER root

RUN apk add --no-cache \
    git \
    curl \
    bash \
    jq \
    yq \
    github-cli \
    less

RUN addgroup -g 998 docker || true && \
    addgroup -S jenkins && \
    adduser -S -G jenkins -g jenkins jenkins && \
    adduser jenkins docker

RUN mkdir -p /home/jenkins && chown jenkins:jenkins /home/jenkins
ENV HOME=/home/jenkins

ENV PS4='+ $(date "+%T") [$(whoami)] '
ENV SHELLOPTS=xtrace

RUN printf '#!/bin/sh\nset -x\nexec /bin/sh "$@"\n' > /usr/local/bin/sh-wrapper
RUN chmod +x /usr/local/bin/sh-wrapper
ENV SHELL=/usr/local/bin/sh-wrapper

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

USER jenkins