package Untouchables;

public final class WebGadget {

	private String commandParameter;
	private String commandArgument;
	private Object targetVariable;

	public WebGadget(String commandParameter, String commandArgument,
			Object targetVariable) {
		this.commandParameter = commandParameter;
		this.commandArgument = commandArgument;
		this.targetVariable = targetVariable;
	}

	public String parameter(String parameterName) {
		if (parameterName.equals("command"))
			return commandParameter;
		else
			return commandArgument;
	}

	public Object variable(String variableName) {
		return targetVariable;
	}

	public void writeLine(String message) {
		System.out.println(message);
	}

}
