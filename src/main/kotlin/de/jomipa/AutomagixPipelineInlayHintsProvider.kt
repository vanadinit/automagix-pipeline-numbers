package de.jomipa

import com.intellij.codeInsight.hints.declarative.*
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.yaml.psi.YAMLDocument
import org.jetbrains.yaml.psi.YAMLFile
import org.jetbrains.yaml.psi.YAMLKeyValue
import org.jetbrains.yaml.psi.YAMLMapping
import org.jetbrains.yaml.psi.YAMLSequence
import org.jetbrains.yaml.psi.YAMLSequenceItem

class AutomagixPipelineInlayHintsProvider : InlayHintsProvider {
    companion object {
        const val PROVIDER_ID = "automagix.pipeline.numbers"
    }

    override fun createCollector(file: PsiFile, editor: Editor): InlayHintsCollector? {
        if (file !is YAMLFile) return null
        return PipelineCollector()
    }

    private class PipelineCollector : SharedBypassCollector {
        override fun collectFromElement(element: PsiElement, sink: InlayTreeSink) {
            if (element !is YAMLSequenceItem) return

            val sequence = element.parent as? YAMLSequence ?: return
            val keyValue = sequence.parent as? YAMLKeyValue ?: return
            if (keyValue.keyText != "pipeline") return

            // Ensure pipeline is at the root level of the YAML document
            val topMapping = keyValue.parent as? YAMLMapping ?: return
            val doc = topMapping.parent as? YAMLDocument ?: return
            if (doc.parent !is YAMLFile) return

            val index = element.itemIndex
            if (index < 0) return

            sink.addPresentation(
                position = InlineInlayPosition(offset = element.textRange.startOffset, relatedToPrevious = false),
                payloads = null,
                tooltip = "Automagix pipeline index #$index",
                hintFormat = HintFormat.default
            ) {
                text("$index")
            }
        }
    }
}
