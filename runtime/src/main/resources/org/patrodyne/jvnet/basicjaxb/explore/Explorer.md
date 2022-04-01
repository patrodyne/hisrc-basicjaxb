# An exhibit of Markdown TEST TEST

This note demonstrates some of what [Markdown][1] is capable of doing.

*Note: Feel free to play with this page. Unlike regular notes, this doesn't automatically save itself.*

## Basic formatting

Paragraphs can be written like so. A paragraph is the basic block of Markdown. A paragraph is what text will turn into when there is no reason it should become anything else.

Paragraphs must be separated by a blank line.

Basic formatting of *italics*, **bold** and __strong__ is supported. This *can be **nested** like* so. Currently, mistakes *cannot* be ~~scratched.~~; however...

You can also make `inline code` to add code into other things. Also see **Code Block** below.

Tag formatting can be used to <s>strike out</s> a mistake or <u>highlight</u> a point.

## Lists

### Ordered list

1. Item 1
	1. Sub-item 1
	2. Sub-item 2
2. A second item
3. Number 3
4. Ⅳ

*Note: the fourth item uses the Unicode character for [Roman numeral four][2].*

### Unordered list

* An item
	* A sub-item
	* Another sub-item
* Another item
* Yet another item
* And there's more...

#### Plus/Minus bullets

+ An item
	- A sub-item
	- Another sub-item

### Definition list

*ReText*
:  A semantic personal publishing platform 

*Markdown*
:  Text-to-HTML conversion tool

## Paragraph Modifiers

### Code blocks

#### * Indent by tab or 4 spaces

	Code blocks are very useful for developers and other people
	who look at code or other things that are written in plain text.
	As you can see, it uses a fixed-width font.

#### * Wrap in 3+ tildas or backticks


```
Code blocks are very useful for developers and other people
who look at code or other things that are written in plain text.
As you can see, it uses a fixed-width font.
```

Button ...

	#button {
		border: none;
	}

... example.


#### * Wide block

```
         1         2         3         4         5         6         7         8         9        10         1         2         3         4         5         6         7         8         9        10
12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
```

### Quote

> Here is a quote. What this is -- should be self explanatory. Quotes are automatically indented when they are used.

## Headings

There are six levels of headings. They correspond with the six levels of HTML headings. You've probably noticed them already in the page. Each level down uses one more hash character.

### Headings *can* also contain <u>formatting</u>

### They can even contain `inline code`

I don't recommend using more than three or four levels of headings here, because, when you're smallest heading isn't too small, and you're largest heading isn't too big, and you want each size up to look noticeably larger and more important, there there are only so many sizes that you can use.

Alternative Heading
===================

Alternatively headings can be underscored using === and ---.

Alternative Sub-Heading
-----------------------

Of course, demonstrating what headings look like messes up the structure of the page.

## Abbreviations

Markdown converts text to HTML (hover to see the definition).

*Note: Definitions can be anywhere in the document.*

*[HTML]: HyperText Markup Language

## URLs

URLs can be made in a handful of ways:

* A named link to [MarkItDown][3]. The easiest way to do these is to select what you want to make a link and hit `Ctrl+L`.
* Another named link to [MarkItDown](http://www.markitdown.net/)
* Sometimes you just want a URL like <http://www.markitdown.net/>.

## Footnotes

Footnotes [^1] will be added to the bottom of the document.

## Horizontal rule

A horizontal rule is a line that goes across the middle of the page.

---

It's sometimes handy for breaking things up.

## Images

Markdown uses an image syntax that is intended to resemble the syntax for links, allowing for two styles: inline and reference.

Inline images looks like this: ![ReText](https://raw.githubusercontent.com/retext-project/retext/master/ReText/icons/retext.png "ReText (Optional Title)")

### Inline Image Syntax:

    An exclamation mark: !;
    followed by a set of square brackets for the alt attribute text;
    followed by a set of parentheses for the URL or path to the image,
    and an optional title attribute enclosed in double or single quotes.

Reference-style images looks like this: ![ReText][6]

#### Reference Image Syntax:

    “id” is the name of a defined image reference.

As of this writing, Markdown has no syntax for specifying the dimensions of an image; if this is important to you, you can simply use regular HTML `<img>` tags.

<img src="https://raw.githubusercontent.com/retext-project/retext/4ef5eb32d06fcf99d4d314e1b787fcaa2f1f56fc/ReText/icons/retext.svg" alt="ReText" width="100"/>

## Tables

Tables aren't part of the core Markdown spec, but they are part of Github Flavored Markdown (GFM). 

Colons can be used to align columns.

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don't need to make the 
raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

## MathJAX

MathJax supports most of the standard [LaTeX syntax][7], as well as some AMS extensions. You can use both LaTeX- and TeX-styles of boundaries:

	\( LaTeX inline formula \)		$ Plain TeX inline formula (not recommended) $
	\[ LaTeX standalone formula \]	$$ Plain TeX standalone formula $$

### Example: Tex

	$$ x = \frac{-b \pm \sqrt{b^{2} -4ac}}{2a} $$

$$ x = \frac{-b \pm \sqrt{b^{2} -4ac}}{2a} $$

By default, all delimiters except the single-dollar ones ($...$) are enabled. If you want to enable the single-dollar delimiter, go to Preferences and add `mathjax` to "Markdown syntax extensions" field. This is not recommended because then you wan't be able use more than one "real" dollar sign in your document. 

### Example: `\begin...\end` expressions:

	\begin{eqnarray}
	x' &=& &x \sin\phi &+& z \cos\phi \\
	z' &=& - &x \cos\phi &+& z \sin\phi \\
	\end{eqnarray}

\begin{eqnarray}
x' &=& &x \sin\phi &+& z \cos\phi \\
z' &=& - &x \cos\phi &+& z \sin\phi \\
\end{eqnarray}


## Finally

There's actually a lot more to Markdown than this. See the official [introduction][4] and [syntax][5] for more information. However, be aware that this is not using the official implementation, and this might work subtly differently in some of the little things.

<!-- Footnotes -->

[^1]: A footnote with a link back to the original reference

<!-- References -->

  [1]: http://daringfireball.net/projects/markdown/
  [2]: http://www.fileformat.info/info/unicode/char/2163/index.htm
  [3]: http://www.markitdown.net/
  [4]: http://daringfireball.net/projects/markdown/basics
  [5]: http://daringfireball.net/projects/markdown/syntax
  [6]: https://raw.githubusercontent.com/retext-project/retext/master/ReText/icons/retext.png "ReText (Optional Title)"
  [7]: https://en.wikibooks.org/wiki/LaTeX/Mathematics
