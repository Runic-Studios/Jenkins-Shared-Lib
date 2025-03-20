static def call() {
    return """
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: jenkins-agent
        image: registry.runicrealms.com/jenkins-agent:latest
        command:
        - cat
        tty: true
        securityContext:
          privileged: true
        volumeMounts:
        - name: docker-sock
          mountPath: /var/run/docker.sock
      volumes:
      - name: docker-sock
        hostPath:
          path: /var/run/docker.sock
    """
}
