def call(String project, String gitUrl, String gitBranch, String gitCommit) {
    withCredentials([string(credentialsId: 'discord-webhook', variable: 'WEBHOOK_URL')]) {
        discordSend webhookURL: WEBHOOK_URL,
                title: "Build Success: ${project} ✅",
                description: "Jenkins passed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
                footer: "Runic Realms Jenkins",
                result: "SUCCESS"
    }
}