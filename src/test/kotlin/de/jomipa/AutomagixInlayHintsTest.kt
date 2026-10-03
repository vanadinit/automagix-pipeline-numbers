package de.jomipa

import com.intellij.testFramework.utils.inlays.declarative.DeclarativeInlayHintsProviderTestCase
import org.jetbrains.yaml.YAMLLanguage

class AutomagixInlayHintsTest : DeclarativeInlayHintsProviderTestCase() {
    fun testPipelineNumbers() {
        val content = """
            name: Automagix Test
            pipeline:
              /*<# 0 #>*/- python: first
              /*<# 1 #>*/- python: second
            cleanup:
              - python: third
        """.trimIndent()

        doTestProvider("test.yaml", content, AutomagixPipelineInlayHintsProvider())
    }

    fun testNestedPipelineIgnored() {
        val content = """
            nested:
              pipeline:
                - step1
                - step2
        """.trimIndent()

        doTestProvider("test.yaml", content, AutomagixPipelineInlayHintsProvider())
    }

    fun testPreview() {
        val preview = """
            name: Example Automagix Pipeline
            pipeline:
              /*<# 0 #>*/- python: print("First step")
              /*<# 1 #>*/- local: echo "Second step"
        """.trimIndent()

        doTestPreview(preview, AutomagixPipelineInlayHintsProvider.PROVIDER_ID, AutomagixPipelineInlayHintsProvider(), YAMLLanguage.INSTANCE)
    }
}
