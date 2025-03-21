static def call(String registry) {
    return """
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: jenkins-agent
        image: ${registry}/jenkins-agent:latest
        command:
        - cat
        tty: true
        securityContext:
          privileged: true
          runAsUser: 0
        volumeMounts:
        - name: docker-sock
          mountPath: /var/run/docker.sock
      imagePullSecrets:
      - name: regcred
      volumes:
      - name: docker-sock
        hostPath:
          path: /var/run/docker.sock
    """
}
