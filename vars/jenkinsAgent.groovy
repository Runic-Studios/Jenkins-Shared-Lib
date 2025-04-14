static def call(String image) {
    return """
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: jenkins-agent
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
      imagePullSecrets:
      - name: regcred
      volumes:
      - name: docker-sock
        hostPath:
          path: /var/run/docker.sock
    """
}
