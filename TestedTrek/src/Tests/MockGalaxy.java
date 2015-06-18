package Tests;

import java.util.HashMap;

import StarTrek.Galaxy;

public class MockGalaxy extends Galaxy {
    private HashMap<String, Object> stuff = new HashMap<String, Object>();
    private StringBuffer buffer = new StringBuffer();
    public MockGalaxy() {
        super(null);
    }

    public String parameter(String parameterName) {
        return (String)stuff.get(parameterName);
    }

    public Object variable(String variableName) {
        return stuff.get(variableName);
    }

    public void writeLine(String message) {
    	String fakeNewLine = " || ";
        buffer.append(message + fakeNewLine);
    }

    protected void setValueForTesting(String key, Object value) {
        stuff.put(key, value);
    }

    String getAllOutput() {
        return buffer.toString();
    }
}
