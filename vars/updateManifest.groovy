def call(String branch, String repository, String repositoryPath, String imageName, String tag, String tagPath) {
    withCredentials([sshUserPrivateKey(credentialsId: 'github-ssh', keyFileVariable: 'SSH_KEY')]) {
        sh """
            rm -rf ${repository}
            export GIT_SSH_COMMAND='ssh -i $SSH_KEY -o StrictHostKeyChecking=no'
            git clone --branch ${branch} git@github.com:Runic-Studios/${repository}.git ${repository}

            echo "Updating ${repositoryPath}..."
            yq eval -i '.${tagPath} = "${tag}"' ${repository}/${repositoryPath}

            git config --global user.email "runicrealms.mc@gmail.com"
            git config --global user.name "RunicRealmsGithub"

            cd ${repository}
            git add -A
            git commit -m "Update ${imageName} newTag to ${tag}" || true
            git push origin ${branch}

            rm -rf ${repository}
        """
    }
}
