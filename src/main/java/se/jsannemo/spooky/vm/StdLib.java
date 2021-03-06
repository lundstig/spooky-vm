package se.jsannemo.spooky.vm;

import java.util.Random;
import se.jsannemo.spooky.compiler.codegen.Conventions;

/**
 * {@link StdLib} provides the Spooky standard library and utility methods to implement extern
 * functions with the same calling convention.
 */
public final class StdLib {

  private static final Random RANDOM = new Random();

  private StdLib() {}

  /**
   * Returns the argument with offset {@code offset} from the back.
   *
   * @throws VmException if the stack pointer or the argument is out of bounds.
   */
  public static int getArg(SpookyVm vm, int offset) throws VmException {
    int sp = vm.getM(Conventions.STACK_POINTER.absStack());
    return vm.getM(sp - offset);
  }

  /**
   * Set the return value of a call with argument size {@code argSize} to {@code value}
   *
   * @throws VmException if not enough stack is reserved for the return value.
   */
  public static void setReturn(SpookyVm vm, int argSize, int value) throws VmException {
    int sp = vm.getM(Conventions.STACK_POINTER.absStack());
    vm.setM(sp - argSize - 1, value);
  }

  static void random(SpookyVm vm) throws VmException {
    setReturn(vm, 0, RANDOM.nextInt());
  }

  static void printChar(SpookyVm vm) throws VmException {
    vm.getStdOut().print((char) getArg(vm, 1));
  }

  static void printInt(SpookyVm vm) throws VmException {
    vm.getStdOut().print(getArg(vm, 1));
  }
}
