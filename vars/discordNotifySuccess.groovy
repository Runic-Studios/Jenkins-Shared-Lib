def call(String project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Success: ${project} ✅",
            description: "Jenkins passed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "SUCCESS"
}