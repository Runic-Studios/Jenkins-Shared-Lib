static def call() {
    def podYaml = ''

    withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        def dockerCfg = """
        {
          "auths": {
            "registry.runicrealms.com": {
              "username": "${DOCKER_USER}",
              "password": "${DOCKER_PASS}",
              "auth": "${"${DOCKER_USER}:${DOCKER_PASS}".bytes.encodeBase64().toString()}"
            }
          }
        }
        """.stripIndent().trim()

        writeFile file: 'docker-config.json', text: dockerCfg

        sh '''
          kubectl delete secret regcred -n jenkins --ignore-not-found
          kubectl create secret generic regcred \
            --from-file=.dockerconfigjson=docker-config.json \
            --type=kubernetes.io/dockerconfigjson \
            -n jenkins
        '''

        podYaml = """
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
          imagePullSecrets:
          - name: regcred
          volumes:
          - name: docker-sock
            hostPath:
              path: /var/run/docker.sock
        """
    }

    return podYaml
}
