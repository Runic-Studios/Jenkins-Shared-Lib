static def call(Map<String, String> containers) {
    def containerYaml = containers.collect { name, image ->
        """\
  - name: ${name}
    image: ${image}
    command:
    - cat 
    tty: true
    securityContext:
      privileged: true
      runAsUser: 0
    volumeMounts:
    - name: docker-sock
      mountPath: /var/run/docker.sock
"""
    }.join("")

    return """\
apiVersion: v1
kind: Pod
spec:
  dnsPolicy: None
  dnsConfig:
    nameservers:
      - 1.1.1.1
      - 8.8.8.8
  containers:
${containerYaml}  imagePullSecrets:
  - name: regcred
  volumes:
  - name: docker-sock
    hostPath:
      path: /var/run/docker.sock
""".stripIndent()
}
