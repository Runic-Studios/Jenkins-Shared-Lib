def call(String imageName, String tag, String registry) {
    withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'REGISTRY_USER', passwordVariable: 'REGISTRY_PASSWORD')]) {
        sh """
            echo "Logging into registry..."
            echo $REGISTRY_PASSWORD | docker login ${registry} -u $REGISTRY_USER --password-stdin

            echo "Building Docker image..."
            docker build -t ${registry}/${imageName}:${tag} .

            echo "Pushing Docker image..."
            docker push ${registry}/${imageName}:${tag}

            echo "Image pushed: ${registry}/${imageName}:${tag}"
        """
    }
}
