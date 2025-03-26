def call() {
    withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
        sh "gradle publish -PnexusUsername=${NEXUS_USERNAME} -PnexusPassword=${NEXUS_PASSWORD}"
    }
}