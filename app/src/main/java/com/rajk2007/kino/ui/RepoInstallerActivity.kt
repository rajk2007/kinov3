package com.rajk2007.kino.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lagradost.cloudstream3.MainActivity

class RepoInstallerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val settings = getSharedPreferences("kino_settings", Context.MODE_PRIVATE)
            val reposInstalled = settings.getBoolean("repos_installed", false)

            if (!reposInstalled) {
                val repoList = """
                    [
                        {"name": "Mega Repository", "url": "https://raw.githubusercontent.com/self-similarity/MegaRepo/builds/repo.json"},
                        {"name": "CloudStream Providers", "url": "https://raw.githubusercontent.com/recloudstream/extensions/master/repo.json"},
                        {"name": "Phisher Repo", "url": "https://raw.githubusercontent.com/phisher98/cloudstream-extensions-phisher/refs/heads/builds/repo.json"},
                        {"name": "Megix Repo", "url": "https://raw.githubusercontent.com/SaurabhKaperwan/CSX/builds/CS.json"}
                    ]
                """.trimIndent()
                
                settings.edit().apply {
                    putString("repository_list", repoList)
                    putBoolean("repos_installed", true)
                    apply()
                }
            }
        } catch (e: Exception) {
            // Ignore errors to ensure handoff
        } finally {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
