package de.jomipa

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.yaml.psi.YAMLFile
import org.jetbrains.yaml.psi.YAMLSequence
import org.jetbrains.yaml.psi.YAMLMapping

class AutomagixFullFileTest : BasePlatformTestCase() {
    fun testEntireTestYaml() {
        val stream = javaClass.getResourceAsStream("/test.yaml")
            ?: error("test.yaml not found in test resources")
        val content = stream.bufferedReader().use { it.readText() }

        val file = myFixture.configureByText("test.yaml", content) as YAMLFile
        val doc = file.documents.firstOrNull()
        assertNotNull(doc)
        val topMapping = doc?.topLevelValue as? YAMLMapping
        assertNotNull(topMapping)
        val pipelineKv = topMapping?.getKeyValueByKey("pipeline")
        assertNotNull(pipelineKv)
        val sequence = pipelineKv?.value as? YAMLSequence
        assertNotNull(sequence)
        assertEquals(36, sequence?.items?.size)

        for ((index, item) in sequence!!.items.withIndex()) {
            assertEquals(index, item.itemIndex)
        }
    }
}
