def call(String project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Started: ${project} 🚀",
            description: "Jenkins for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "SUCCESS"
}