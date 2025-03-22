def call(String artifactName, String tag, String tagSuffix, String path, String registry, String registryProject) {
    withCredentials([usernamePassword(credentialsId: 'docker-registry-credentials', usernameVariable: 'REGISTRY_USER', passwordVariable: 'REGISTRY_PASSWORD')]) {
        sh """
            echo "Logging into registry..."
            oras login ${registry} -u '$REGISTRY_USER' -p '$REGISTRY_PASSWORD' --insecure

            echo "Pushing artifact..."
            oras push ${registry}/${registryProject}/${artifactName}:${tag}${tagSuffix} --artifact-type application/java-archive ${path}
            oras push ${registry}/${registryProject}/${artifactName}:latest${tagSuffix} --artifact-type application/java-archive ${path}
        """
    }
}