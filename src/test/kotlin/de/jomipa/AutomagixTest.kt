package de.jomipa

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.yaml.psi.YAMLFile
import org.jetbrains.yaml.psi.YAMLSequenceItem
import org.jetbrains.yaml.psi.YAMLSequence
import org.jetbrains.yaml.psi.YAMLKeyValue
import org.jetbrains.yaml.psi.YAMLMapping
import org.jetbrains.yaml.psi.YAMLDocument

class AutomagixTest : BasePlatformTestCase() {
    fun testYamlPsi() {
        val file = myFixture.configureByText("test.yaml", """
            pipeline:
              - python: first
              - python: second
        """.trimIndent()) as YAMLFile

        val doc = file.documents.firstOrNull()
        assertNotNull(doc)
        val topMapping = doc?.topLevelValue as? YAMLMapping
        assertNotNull(topMapping)
        val pipelineKv = topMapping?.getKeyValueByKey("pipeline")
        assertNotNull(pipelineKv)
        val sequence = pipelineKv?.value as? YAMLSequence
        assertNotNull(sequence)
        assertEquals(2, sequence?.items?.size)

        for (item in sequence!!.items) {
            println("Item index: ${item.itemIndex}, textRange: ${item.textRange}, text: '${item.text}'")
            for (child in item.node.getChildren(null)) {
                println("  child: ${child.elementType} '${child.text}' range=${child.textRange}")
            }
        }
    }
}
