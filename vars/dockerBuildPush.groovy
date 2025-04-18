def call(String dockerfile, String imageName, String tag, String registry, String registryProject) {
    withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'REGISTRY_USER', passwordVariable: 'REGISTRY_PASSWORD')]) {
        sh """
            echo "Logging into registry..."
            echo $REGISTRY_PASSWORD | docker login ${registry} -u '$REGISTRY_USER' --password-stdin

            echo "Building Docker image..."
            docker build -f ${dockerfile} -t ${registry}/${registryProject}/${imageName}:${tag} .

            echo "Pushing Docker image..."
            docker push ${registry}/${registryProject}/${imageName}:${tag}

            echo "Image pushed: ${registry}/${registryProject}/${imageName}:${tag}"
        """
    }
}
