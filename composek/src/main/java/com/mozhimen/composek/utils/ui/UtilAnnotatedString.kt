package com.mozhimen.composek.utils.ui

import android.graphics.Typeface
import android.text.Editable
import android.text.Html.ImageGetter
import android.text.Html.TagHandler
import android.text.Layout
import android.text.Spanned
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.Spanned.SPAN_MARK_MARK
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.util.fastForEach
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import org.xml.sax.Attributes
import org.xml.sax.ContentHandler
import org.xml.sax.XMLReader

/**
 * @ClassName UtilAnnotatedString
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/5/13
 * @Version 1.0
 */

/**
 * Converts a string with HTML tags into [AnnotatedString].
 *
 * If you define your string in the resources, make sure to use HTML-escaped opening brackets
 * <code>&amp;lt;</code> instead of <code><</code>.
 *
 * For a list of supported tags go check
 * [Styling with HTML markup](https://developer.android.com/guide/topics/resources/string-resource#StylingWithHTML)
 * guide.
 *
 * To support nested bullet list, the nested sub-list wrapped in <ul> tag MUST be placed inside a
 * list item with a tag <li>. In other words, you must add wrapped sub-list in between opening and
 * closing <li> tag of the wrapping sub-list. This is due to the specificities of the underlying
 * XML/HTML parser.
 *
 * @param htmlString HTML-tagged string to be parsed to construct AnnotatedString
 * @param linkStyles style configuration to be applied to links present in the string in different
 *   styles
 * @param linkInteractionListener a listener that will be attached to links that are present in the
 *   string and triggered when user clicks on those links. When set to null, which is a default, the
 *   system will try to open the corresponding links with the
 *   [androidx.compose.ui.platform.UriHandler] composition local
 *
 * Note that any link style passed directly to this method will be merged with the styles set
 * directly on a HTML-tagged string. For example, if you set a color of the link via the span
 * annotation to "red" but also pass a green color via the [linkStyles], the link will be displayed
 * as green. If, however, you pass a green background via the [linkStyles] instead, the link will be
 * displayed as red on a green background.
 *
 * Example of displaying styled string from resources
 *
 * @sample androidx.compose.ui.text.samples.AnnotatedStringFromHtml
 * @see LinkAnnotation
 */
fun AnnotatedString.Companion.fromHtml(
    htmlString: String,
    linkStyles: TextLinkStyles? = null,
    linkInteractionListener: LinkInteractionListener? = null,
): AnnotatedString {
    // Check ContentHandlerReplacementTag kdoc for more details
    val spanned: Spanned = htmlString.parseAsHtml(HtmlCompat.FROM_HTML_MODE_COMPACT, null, null)
    UtilKLogWrapper.d("UtilAnnotatedString>>>>>", "fromHtml: $spanned")
    return spanned.toAnnotatedString(linkStyles, linkInteractionListener)
}

fun Spanned.toAnnotatedString(
    linkStyles: TextLinkStyles? = null,
    linkInteractionListener: LinkInteractionListener? = null,
): AnnotatedString {
    return AnnotatedString.Builder(capacity = length)
        .append(this)
        .also { it.addSpans(this, linkStyles, linkInteractionListener) }
        .toAnnotatedString()
}

fun AnnotatedString.Builder.addSpans(
    spanned: Spanned,
    linkStyles: TextLinkStyles?,
    linkInteractionListener: LinkInteractionListener?,
) {
    spanned.getSpans(0, length, Any::class.java).forEach { span ->
        val range = TextRange(spanned.getSpanStart(span), spanned.getSpanEnd(span))
        addSpan(span, range.start, range.end, linkStyles, linkInteractionListener)
    }
}

fun AnnotatedString.Builder.addSpan(
    span: Any,
    start: Int,
    end: Int,
    linkStyles: TextLinkStyles?,
    linkInteractionListener: LinkInteractionListener?,
) {
    when (span) {
        is AbsoluteSizeSpan -> {
            // TODO(soboleva) need density object or make dip/px new units in TextUnit
        }

        is AlignmentSpan -> {
            addStyle(span.toParagraphStyle(), start, end)
        }

        is AnnotationSpan -> {
            addStringAnnotation(span.key, span.value, start, end)
        }

        is BackgroundColorSpan -> {
            addStyle(SpanStyle(background = Color(span.backgroundColor)), start, end)
        }

        is ForegroundColorSpan -> {
            addStyle(SpanStyle(color = Color(span.foregroundColor)), start, end)
        }

        is RelativeSizeSpan -> {
            addStyle(SpanStyle(fontSize = span.sizeChange.em), start, end)
        }

        is StrikethroughSpan -> {
            addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), start, end)
        }

        is StyleSpan -> {
            span.toSpanStyle()?.let { addStyle(it, start, end) }
        }

        is SubscriptSpan -> {
            addStyle(SpanStyle(baselineShift = BaselineShift.Subscript), start, end)
        }

        is SuperscriptSpan -> {
            addStyle(SpanStyle(baselineShift = BaselineShift.Superscript), start, end)
        }

        is TypefaceSpan -> {
            addStyle(span.toSpanStyle(), start, end)
        }

        is UnderlineSpan -> {
            addStyle(SpanStyle(textDecoration = TextDecoration.Underline), start, end)
        }

        is URLSpan -> {
            span.url?.let { url ->
                val link = LinkAnnotation.Url(url, linkStyles, linkInteractionListener)
                addLink(link, start, end)
            }
        }

//        is BulletSpanWithLevel -> {
//            addBullet(
//                start = start,
//                end = end,
//                indentation = DefaultBulletIndentation * span.indentationLevel,
//                bullet = span.bullet
//            )
//
//            val bulletParStyle = ParagraphStyle(textIndent = TextIndent(DefaultBulletIndentation * span.indentationLevel, DefaultBulletIndentation * span.indentationLevel))
//            addStyle(style = bulletParStyle, start = start, end = end)
//            annotations.add(MutableRange(item = bullet, start = start, end = end))
//        }
    }
}

fun AlignmentSpan.toParagraphStyle(): ParagraphStyle {
    val alignment =
        when (this.alignment) {
            Layout.Alignment.ALIGN_NORMAL -> TextAlign.Start
            Layout.Alignment.ALIGN_CENTER -> TextAlign.Center
            Layout.Alignment.ALIGN_OPPOSITE -> TextAlign.End
            else -> TextAlign.Unspecified
        }
    return ParagraphStyle(textAlign = alignment)
}

fun StyleSpan.toSpanStyle(): SpanStyle? {
    /**
     * StyleSpan doc: styles are cumulative -- if both bold and italic are set in separate spans, or
     * if the base style is bold and a span calls for italic, you get bold italic. You can't turn
     * off a style from the base style.
     */
    return when (style) {
        Typeface.BOLD -> {
            SpanStyle(fontWeight = FontWeight.Bold)
        }

        Typeface.ITALIC -> {
            SpanStyle(fontStyle = FontStyle.Italic)
        }

        Typeface.BOLD_ITALIC -> {
            SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
        }

        else -> null
    }
}

fun TypefaceSpan.toSpanStyle(): SpanStyle {
    val fontFamily =
        when (family) {
            FontFamily.Cursive.name -> FontFamily.Cursive
            FontFamily.Monospace.name -> FontFamily.Monospace
            FontFamily.SansSerif.name -> FontFamily.SansSerif
            FontFamily.Serif.name -> FontFamily.Serif
            else -> {
                optionalFontFamilyFromName(family)
            }
        }
    return SpanStyle(fontFamily = fontFamily)
}

/**
 * Mirrors [androidx.compose.ui.text.font.PlatformTypefaces.optionalOnDeviceFontFamilyByName]
 * behavior with both font weight and font style being Normal in this case
 */
fun optionalFontFamilyFromName(familyName: String?): FontFamily? {
    if (familyName.isNullOrEmpty()) return null
    val typeface = Typeface.create(familyName, Typeface.NORMAL)
    return typeface
        .takeIf {
            typeface != Typeface.DEFAULT &&
                    typeface != Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        }
        ?.let { FontFamily(it) }
}

class AnnotationSpan(val key: String, val value: String)

private const val AnnotationTag = "annotation"
private const val Li = "li"
private const val Ul = "ul"