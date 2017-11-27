package domParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.commons.lang3.StringUtils;


/**
 * This class can be used to create an element tree as a part of a DOM parser.
 * It also implements the findElementsByClassName() to search for all elements
 * matching a given class name
 * 
 * @author Nidhi Venkatesh
 * @version 1.0
 *
 */
public class Element {

	private String classNames; //classNames can be multiple space separated names
	private String name; 
	private List<Element> children;

	public Element() {
		classNames = StringUtils.EMPTY;
		name = StringUtils.EMPTY;
		children = new LinkedList<>();
	}
	
	public String getClassNames() {
		return classNames;
	}
	
	public String getElementName() {
		return name;
	}
	
	public List<Element> getChildren() {
		return children;
	}
	
	/**
	 * Method to add class names to an element
	 * @param classNamesToBeAdded string to be added as class name to the element
	 */
	public void addClassNames(String classNamesToBeAdded) {
		if(classNamesToBeAdded == null)
			throw new IllegalArgumentException("Class names cannot be null");
		
		if(StringUtils.isEmpty(classNames))
			classNames += classNamesToBeAdded.trim();
		else
			classNames += " " + classNamesToBeAdded.trim();
	}
	
	/**
	 * Method to add a child element to existing element
	 * @param childElement child element to be added
	 */
	public void addChildElement(Element childElement) {
		children.add(childElement);
	}
	
	/**
	 * Method to find all elements using their class name that match the search string
	 * @param element object reference to start the search from
	 * @param classNameToBeMatched search string to be matched
	 * @return list of elements whose class name matches the search string
	 */
	protected static List<Element> findElementsByClassName(Element element, String classNameToBeMatched) {
		if(element == null)
			return null;
		
		if(classNameToBeMatched == null)
			throw new IllegalArgumentException("Class names cannot be null");
		
		Queue<Element> queue = new LinkedList<>();
		List<Element> retList = new LinkedList<>();
		
		queue.add(element);	
		// Doing a BFS traversal to find the class name to be matched
		while (!queue.isEmpty()) {
			
			Element curr = queue.remove();
			
			if(doClassnamesMatch(curr.getClassNames(), classNameToBeMatched))
				retList.add(curr);

			List<Element> children = curr.getChildren();
			queue.addAll(children);
		}
		return retList;
	}
	
	/**
	 * Method to check if element class name matches with the search match string
	 * @param classNames space separated class names of an element
	 * @param classNameToBeMatched search string to be matched
	 * @return
	 */
	private static boolean doClassnamesMatch(final String classNames, final String classNameToBeMatched) {
		
		if(StringUtils.isEmpty(classNames) && StringUtils.isEmpty(classNameToBeMatched))
			return true;
		
		else if(StringUtils.isEmpty(classNames) || StringUtils.isEmpty(classNameToBeMatched))
			return false;
	
		return classNames.contains(classNameToBeMatched);
	}
}
