def call( project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Failed: ${project} ❌",
            description: "Jenkins failed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "FAILURE"
}