static def call(Map<String, String>... containers) {
    def containerYaml = containers.collect { c ->
        """\
  - name: ${c.key}
    image: ${c.value}
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
  containers:
${containerYaml}  imagePullSecrets:
  - name: regcred
  volumes:
  - name: docker-sock
    hostPath:
      path: /var/run/docker.sock
""".stripIndent()
}
