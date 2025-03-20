def call(String deploymentBranch, String imageName, String tag, String registry) {
    withCredentials([sshUserPrivateKey(credentialsId: 'github-ssh', keyFileVariable: 'SSH_KEY')]) {
        sh """
            rm -rf Realm-Deployment
            export GIT_SSH_COMMAND='ssh -i $SSH_KEY -o StrictHostKeyChecking=no'
            git clone --branch ${deploymentBranch} git@github.com:Runic-Studios/Realm-Deployment.git Realm-Deployment

            echo "Updating kustomization.yaml..."
            yq eval -i '(.. | select(has("name") and .name == "${registry}/${imageName}").newTag) = "${tag}"' Realm-Deployment/base/kustomization.yaml

            cd Realm-Deployment
            git add base/kustomization.yaml
            git commit -m "Update ${imageName} image to ${tag}"
            git push origin ${deploymentBranch}

            rm -rf Realm-Deployment
        """
    }
}
