/**
 * 
 */
package domParser;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

/**
 * This is a junit testcase to test the different methods that are a part of class Element
 * @author Nidhi Venkatesh
 * @version 1.0
 *
 */
public class ElementRetrievalTest {
		
	@Test
	public void addChildElementMethodAddsANewElementToASpecifiedElement() {
		
		Element sampleElement = new Element();
		sampleElement.addClassNames("blog-entry");
		
		Element childElement = new Element();
		childElement.addClassNames("header");
		
		sampleElement.addChildElement(childElement);
		
		assertEquals(1,sampleElement.getChildren().size());
		
		Element child1 = new Element();
		child1.addClassNames("item");
		childElement.addChildElement(child1);
		
		Element child2 = new Element();
		child1.addClassNames("snippet");
		childElement.addChildElement(child2);
		
		assertEquals(2,childElement.getChildren().size());				
	}

	@Test (expected = IllegalArgumentException.class)
	public void addClassNamesMethodThrowsExceptionForANullClassNameAddition() {
		
		Element sampleElement = new Element();
		sampleElement.addClassNames(null);
	        
	}
	
	@Test
	public void addClassNamesMethodAddsASpecificClassNameToASpecifiedElement() {
		
		Element sampleElement = new Element();
		sampleElement.addClassNames("header");
		
		assertEquals("header", sampleElement.getClassNames());
		
		sampleElement.addClassNames("content");
		assertEquals("header content", sampleElement.getClassNames());
	}
	
	@Test
	public void getClassNamesMethodReturnsEmptyStringForAElementWithNoClassName() {
		
		Element sampleElement = new Element();
		assertEquals("", sampleElement.getClassNames());
	}
	
	@Test
	public void getClassNamesMethodRetrivesASpecificClassNameFromASpecifiedElement() {
	
		Element sampleElement = new Element();
		sampleElement.addClassNames("header");
		
		assertEquals("header", sampleElement.getClassNames());
		
		Element childElement = new Element();
		childElement.addClassNames("item");
		
		sampleElement.addChildElement(childElement);
		
		List<Element> children = sampleElement.getChildren();
		assertEquals("item", children.get(0).getClassNames());
	}
	
	@Test
	public void findElementsByClassNameMethodFindsMatchingElements() {

		Element sampleElement = new Element();
		sampleElement.addClassNames("layout content");
		
		Element firstChildToSample = new Element();
		firstChildToSample.addClassNames("header");
		sampleElement.addChildElement(firstChildToSample);
		
		Element secondChildToSample = new Element();
		secondChildToSample.addClassNames("title");
		sampleElement.addChildElement(secondChildToSample);
		
		Element firstChildToHeaderElement = new Element();
		firstChildToHeaderElement.addClassNames("item");
		firstChildToSample.addChildElement(firstChildToHeaderElement);
		
		Element secondChildToHeaderElement = new Element();
		secondChildToHeaderElement.addClassNames("snippet");
		firstChildToSample.addChildElement(secondChildToHeaderElement);
		
		firstChildToHeaderElement.addClassNames("title");

		assertEquals(0, Element.findElementsByClassName(sampleElement,"").size());

		assertEquals(0, Element.findElementsByClassName(sampleElement,"abbr").size());
		
		assertEquals(1, Element.findElementsByClassName(sampleElement,"header").size());	
		
		assertEquals(2, Element.findElementsByClassName(sampleElement,"title").size());
		
		assertEquals(1, Element.findElementsByClassName(sampleElement,"item title").size());
	
		assertEquals(0, Element.findElementsByClassName(sampleElement,"Snippet").size());
		
	}
	
}
