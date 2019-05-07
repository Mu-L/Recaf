package me.coley.recaf.parse.assembly;

import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * Abstract base for assembling an instruction from a line of text.
 *
 * @author Matt
 */
public abstract class Assembler {
	/**
	 * Opcode to base assembling off of.
	 */
	protected final int opcode;

	public Assembler(int opcode) {
		this.opcode = opcode;
	}

	/**
	 * Parse the instruction text.
	 *
	 * @param text
	 * 		Instruction text <i>(Opcode prefix removed)</i>
	 *
	 * @return Instruction instance.
	 */
	public abstract AbstractInsnNode parse(String text);

	/**
	 * Throws an exception with the given failure information.
	 *
	 * @param text
	 * 		Input text that failed to be parsed.
	 *
	 * @return Dummy return to satisfy usage in {@link #parse(String)}.
	 */
	protected AbstractInsnNode fail(String text) {
		return fail(text, null);
	}

	/**
	 * Throws an exception with the given failure information.
	 *
	 * @param text
	 * 		Input text that failed to be parsed.
	 * @param details
	 * 		Additional failure information.
	 *
	 * @return Dummy return to satisfy usage in {@link #parse(String)}.
	 */
	protected AbstractInsnNode fail(String text, String details) {
		String clazz = this.getClass().getSimpleName() + "InsnNode";
		StringBuilder sb = new StringBuilder(clazz + " parse failure: " + text);
		if(details != null)
			sb.append('\n').append(details);
		throw new AssemblyParseException(sb.toString());
	}
}