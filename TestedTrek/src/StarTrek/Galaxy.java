package StarTrek;

import Untouchables.WebGadget;

/**
 * A simple Proxy/wrapper class for Untouchables.WebGadget,
 * allowing us to create, modify, and subclass (or mock) Galaxy.
 * Note that this object currently has no tests, because it does nothing but delegate,
 * and the delegation is not unit-testable.
 * Note also that it is production code.
 */
public class Galaxy {
    private WebGadget webContext;

    public Galaxy(WebGadget webContext) {
        this.webContext = webContext;
    }

    public String parameter(String parameterName) {
        return webContext.parameter(parameterName);
    }

    public Object variable(String variableName) {
        return webContext.variable(variableName);
    }

    public void writeLine(String message) {
        webContext.writeLine(message);
    }
}
