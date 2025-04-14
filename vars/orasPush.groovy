def call(String artifactName, String tag, String path, String registry, String registryProject) {
    withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'REGISTRY_USER', passwordVariable: 'REGISTRY_PASSWORD')]) {
        sh """
            echo "Logging into registry..."
            oras login ${registry} -u '$REGISTRY_USER' -p '$REGISTRY_PASSWORD' --insecure

            echo "Pushing artifact..."
            FILENAME=\$(basename ${path})
            FILEDIR=\$(dirname ${path})
            cd "\$FILEDIR"

            oras push ${registry}/${registryProject}/${artifactName}:${tag} "\$FILENAME"
        """
    }
}