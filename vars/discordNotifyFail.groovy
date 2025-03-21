def call(String project, String gitUrl, String gitBranch, String gitCommit) {
    withCredentials([string(credentialsId: 'discord-webhook', variable: 'WEBHOOK_URL')]) {
        discordSend webhookURL: WEBHOOK_URL,
                title: "Build Failed: ${project} ❌",
                description: "Jenkins failed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
                footer: "Runic Realms Jenkins",
                result: "FAILURE"
    }
}