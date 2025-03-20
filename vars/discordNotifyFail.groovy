def call(String project, String gitUrl, String gitBranch, String gitCommit) {
    discordSend webhookURL: credentials('discord-webhook'),
            title: "Build Failed: ${project} ❌",
            description: "Jenkins failed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "FAILURE"
}