import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Processor p = new Processor();
		p.parse("Program.txt");
		p.getEX_M().put("writeReg",-100);
		p.getEX_M().put("writeReg2",-100);
		p.getEX_M().put("aluOut",-100);
		p.getEX_M().put("aluOut2",-100);
		p.getEX_M().put("regWriteSignal",-100);
		p.getM_WB().put("regWriteSignal",-100);
		boolean flag = false;
		int counter = 0;
		Stack printingStack = new Stack<String>();
		while (counter < 6) {
			p.setTestFlag(false);
			if (p.getMemory().getMem()[p.getPc().getData()] == 0 && !flag) {
				flag = true;
			}
			if(flag && p.getMemory().getMem()[p.getPc().getData()] != 0){
                flag = false;
                counter=0;
            }
			if (flag) {
				counter++;
			}
			p.setCycle(p.getCycle() + 1);
			System.out.println("---------------------------------------");
			System.out.println("Cycle: " + p.getCycle());
			if (p.isWB_Flag()) {
				p.writeBack();
				printingStack.add("Instruction " + p.getM_WB().get("pc") + " is writing back");
			}
			if (p.isMem_Flag() & counter <= 5) {
				p.mem();
				printingStack.add("Instruction " + p.getEX_M().get("pc") + " is accessing memory");
			}
			if (p.isEX_Flag() & !p.isExecutingFlag() & counter <= 4) {
				if (!p.isBranchFlag()) {
					p.execute();
					printingStack.add("Instruction " + p.getID_EX().get("pc") + " is executing");
				}
			}
			if (p.isEX_Flag() & p.isExecutingFlag() & counter <= 4 & p.getExecuteCounter() == 1) {
				printingStack.add("Instruction " + p.getPcTmpEX_M() + " is executing");
			}
			if (p.isID_Flag() & !p.isDecodingFlag() & counter <= 2) {
				if (!p.isBranchFlag()) {
					p.decode();
					printingStack.add("Instruction " + p.getIF_ID().get("pc") + " is decoding");
				}
			}
			if (p.isID_Flag() & p.isDecodingFlag() & counter <= 2 & p.getDecodeCounter() == 1) {
				printingStack.add("Instruction " + p.getPcTmpID_EX() + " is decoding");
			}
			if (p.isEX_Flag() & counter <= 4) {
				p.setExecuteCounter(p.getExecuteCounter() + 1);
			}
			if (p.getExecuteCounter() == 2 & counter <= 4) {
				p.setEX_Flag(false);
				p.setExecutingFlag(false);
				p.setMem_Flag(true);
				p.setExecuteCounter(0);
			}
			if (p.isID_Flag() & counter <= 2) {
				p.setDecodeCounter(p.getDecodeCounter() + 1);
			}
			if (p.getDecodeCounter() == 2 & counter <= 2) {
				p.setID_Flag(false);
				p.setDecodingFlag(false);
				p.setEX_Flag(true);
				p.setDecodeCounter(0);
				p.setIF_Flag(true);
			}
			if ((!p.isTestFlag()) & !p.isID_Flag() & p.isIF_Flag() & counter <= 0) {
				if (!p.isBranchFlag()) {
					p.fetch();
					printingStack.add("Instruction " + p.getPc().getData() + " is fetching");
				}
			}
			while (!printingStack.isEmpty()) {
				System.out.println(printingStack.pop() + "");
			}
		}
		System.out.println("---------------------------------------");
		System.out.print("Register File: {");
		for (int i = 0; i < p.getrFile().getRegFile().length; i++) {
			if (i < p.getrFile().getRegFile().length - 1) {
				System.out.print("R" + i + ": " + p.getrFile().getRegFile()[i].getData() + ", ");
			} else {
				System.out.print("R" + i + ": " + p.getrFile().getRegFile()[i].getData() + "}");
			}
		}
		System.out.println();
		for (int i = 0; i < 2048; i++) {
			System.out.println("Memory cell " + i + ": " + p.getMemory().getMem()[i]);
		}
	}

}