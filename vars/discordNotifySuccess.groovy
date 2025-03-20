def call(String project, String gitUrl, String gitBranch, String gitCommit) {
    discordSend webhookURL: credentials('discord-webhook'),
            title: "Build Success: ${project} ✅",
            description: "Jenkins passed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "SUCCESS"
}