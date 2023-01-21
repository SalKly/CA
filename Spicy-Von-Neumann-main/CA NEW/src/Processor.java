import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Processor {

	private Memory memory;
	private static RegisterFile rFile;
	private ControlUnit cUnit;
	private Alu alu;
	private Register pc;
	private Mux aluMux;
	private Mux shiftMux;
	private Mux jumpMux;
	private Mux branchMux;
	private Mux writeMux;
	private Mux readMux;
	private HashMap<String, Integer> IF_ID;
	private HashMap<String, Integer> ID_EX;
	private HashMap<String, Integer> EX_M;
	private HashMap<String, Integer> M_WB;
	private int cycle;
	private int dataPointer;
	private int instructionPointer;
	private int decodeCounter;
	private int executeCounter;
	private int pcTmpID_EX;
	private int pcTmpEX_M;
	private boolean IF_Flag;
	private boolean ID_Flag;
	private boolean EX_Flag;
	private boolean Mem_Flag;
	private boolean WB_Flag;
	private boolean branchFlag;
	private boolean decodingFlag;
	private boolean executingFlag;
	private boolean testFlag;

	public Processor() {
		this.memory = new Memory(2048);
		this.rFile = new RegisterFile();
		this.cUnit = new ControlUnit();
		this.alu = new Alu();
		this.pc = new Register("pc");
		this.aluMux = new Mux();
		this.shiftMux = new Mux();
		this.jumpMux = new Mux();
		this.branchMux = new Mux();
		this.writeMux = new Mux();
		this.readMux = new Mux();
		this.IF_ID = new HashMap<String, Integer>();
		this.ID_EX = new HashMap<String, Integer>();
		this.EX_M = new HashMap<String, Integer>();
		this.M_WB = new HashMap<String, Integer>();
		this.cycle = 0;
		this.dataPointer = 1024;
		this.instructionPointer = 0;
		this.decodeCounter = 0;
		this.executeCounter = 0;
		this.pcTmpID_EX = 0;
		this.pcTmpEX_M = 0;
		this.IF_Flag = true;
		this.ID_Flag = false;
		this.EX_Flag = false;
		this.Mem_Flag = false;
		this.WB_Flag = false;
		this.branchFlag = false;
		this.decodingFlag = false;
		this.executingFlag = false;
		this.testFlag = false;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public static RegisterFile getrFile() {
		return rFile;
	}

	public static void setrFile(RegisterFile rFile) {
		Processor.rFile = rFile;
	}

	public ControlUnit getcUnit() {
		return cUnit;
	}

	public void setcUnit(ControlUnit cUnit) {
		this.cUnit = cUnit;
	}

	public Alu getAlu() {
		return alu;
	}

	public void setAlu(Alu alu) {
		this.alu = alu;
	}

	public Register getPc() {
		return pc;
	}

	public void setPc(Register pc) {
		this.pc = pc;
	}

	public Mux getAluMux() {
		return aluMux;
	}

	public void setAluMux(Mux aluMux) {
		this.aluMux = aluMux;
	}

	public Mux getShiftMux() {
		return shiftMux;
	}

	public void setShiftMux(Mux shiftMux) {
		this.shiftMux = shiftMux;
	}

	public Mux getJumpMux() {
		return jumpMux;
	}

	public void setJumpMux(Mux jumpMux) {
		this.jumpMux = jumpMux;
	}

	public Mux getBranchMux() {
		return branchMux;
	}

	public void setBranchMux(Mux branchMux) {
		this.branchMux = branchMux;
	}

	public Mux getWriteMux() {
		return writeMux;
	}

	public void setWriteMux(Mux writeMux) {
		this.writeMux = writeMux;
	}

	public Mux getReadMux() {
		return readMux;
	}

	public void setReadMux(Mux readMux) {
		this.readMux = readMux;
	}

	public HashMap<String, Integer> getIF_ID() {
		return IF_ID;
	}

	public void setIF_ID(HashMap<String, Integer> iF_ID) {
		IF_ID = iF_ID;
	}

	public HashMap<String, Integer> getID_EX() {
		return ID_EX;
	}

	public void setID_EX(HashMap<String, Integer> iD_EX) {
		ID_EX = iD_EX;
	}

	public HashMap<String, Integer> getEX_M() {
		return EX_M;
	}

	public void setEX_M(HashMap<String, Integer> eX_M) {
		EX_M = eX_M;
	}

	public HashMap<String, Integer> getM_WB() {
		return M_WB;
	}

	public void setM_WB(HashMap<String, Integer> m_WB) {
		M_WB = m_WB;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getDataPointer() {
		return dataPointer;
	}

	public void setDataPointer(int dataPointer) {
		this.dataPointer = dataPointer;
	}

	public int getInstructionPointer() {
		return instructionPointer;
	}

	public void setInstructionPointer(int instructionPointer) {
		this.instructionPointer = instructionPointer;
	}

	public int getDecodeCounter() {
		return decodeCounter;
	}

	public void setDecodeCounter(int decodeCounter) {
		this.decodeCounter = decodeCounter;
	}

	public int getExecuteCounter() {
		return executeCounter;
	}

	public void setExecuteCounter(int executeCounter) {
		this.executeCounter = executeCounter;
	}

	public int getPcTmpID_EX() {
		return pcTmpID_EX;
	}

	public void setPcTmpID_EX(int pcTmpID_EX) {
		this.pcTmpID_EX = pcTmpID_EX;
	}

	public int getPcTmpEX_M() {
		return pcTmpEX_M;
	}

	public void setPcTmpEX_M(int pcTmpEX_M) {
		this.pcTmpEX_M = pcTmpEX_M;
	}

	public boolean isIF_Flag() {
		return IF_Flag;
	}

	public void setIF_Flag(boolean iF_Flag) {
		IF_Flag = iF_Flag;
	}

	public boolean isID_Flag() {
		return ID_Flag;
	}

	public void setID_Flag(boolean iD_Flag) {
		ID_Flag = iD_Flag;
	}

	public boolean isEX_Flag() {
		return EX_Flag;
	}

	public void setEX_Flag(boolean eX_Flag) {
		EX_Flag = eX_Flag;
	}

	public boolean isMem_Flag() {
		return Mem_Flag;
	}

	public void setMem_Flag(boolean mem_Flag) {
		Mem_Flag = mem_Flag;
	}

	public boolean isWB_Flag() {
		return WB_Flag;
	}

	public void setWB_Flag(boolean wB_Flag) {
		WB_Flag = wB_Flag;
	}

	public boolean isBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}

	public boolean isDecodingFlag() {
		return decodingFlag;
	}

	public void setDecodingFlag(boolean decodingFlag) {
		this.decodingFlag = decodingFlag;
	}

	public boolean isExecutingFlag() {
		return executingFlag;
	}

	public void setExecutingFlag(boolean executingFlag) {
		this.executingFlag = executingFlag;
	}

	public boolean isTestFlag() {
		return testFlag;
	}

	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}

	public void fetch() {
		if (!this.branchFlag) {
			int currentInstruction = memory.getMem()[this.getPc().getData()];
			this.getPc().setData(this.getPc().getData() + 1);
			this.IF_ID.put("instruction", currentInstruction);
			this.IF_ID.put("pc", this.getPc().getData());
			this.IF_Flag = false;
			this.ID_Flag = true;
		}
	}

	public void decode() {
		if (!this.branchFlag) {
			if (!this.decodingFlag) {
				this.pcTmpID_EX = this.IF_ID.get("pc");
				System.out.println("pc:" + this.IF_ID.get("pc"));
			}
			this.decodingFlag = true;
			System.out.println("**********");
			System.out.println("Decoding:");
			System.out.println("pc from fetching:" + this.IF_ID.get("pc"));
			System.out.println("instruction from fetching: " + this.IF_ID.get("instruction"));
			System.out.println("**********");
			int instruction = this.IF_ID.get("instruction");
			int opcode = instruction >>> 28;
			int R1 = (instruction >>> 23) & 0b011111;
			int R2 = (instruction >>> 18) & 0b011111;
			int R3 = (instruction >>> 13) & 0b011111;
			int shamt = instruction & 0b01111111111111;
			int imm = instruction & 0b0111111111111111111;
			int temp = instruction & 0b0100000000000000000;
			temp = temp >> 17;
			if (temp == 1) {
				imm = imm | 0b11111111111111000000000000000000;
			}
			this.cUnit.generateSignals(opcode);
			this.assignReadMux(R3, R1);
			this.ID_EX.put("RSForward",R2);
			this.ID_EX.put("RTForward",this.readMux.getOutput());
			this.ID_EX.put("RDForward",R1);

			this.rFile.setReadReg1(R2);
			this.rFile.setReadReg2(this.readMux.getOutput());
			this.rFile.setRegWriteSignal(this.cUnit.getRegWrite());
			this.rFile.setWriteReg(R1);
			this.assignShiftMux(imm, shamt);
			this.ID_EX.put("instruction", instruction);
			this.ID_EX.put("pc", this.IF_ID.get("pc"));
			this.ID_EX.put("aluOP", this.cUnit.getALUop());
			this.ID_EX.put("regWriteSignal", this.cUnit.getRegWrite());
			this.ID_EX.put("aluSRC", this.cUnit.getALUSrc());
			this.ID_EX.put("memRead", this.cUnit.getMemRead());
			this.ID_EX.put("memWrite", this.cUnit.getMemWrite());
			this.ID_EX.put("memToReg", this.cUnit.getMemToReg());
			this.ID_EX.put("branch", this.cUnit.getBranch());
			this.ID_EX.put("jump", this.cUnit.getJump());
			this.ID_EX.put("shiftMuxOut", this.shiftMux.getOutput());
			this.ID_EX.put("readData1", this.rFile.getReadData1());
			this.ID_EX.put("readData2", this.rFile.getReadData2());
			this.ID_EX.put("writeReg", this.rFile.getWriteReg());
		}
	}

	public void execute() {
		if (!this.branchFlag) {
			if (!this.executingFlag) {
				this.pcTmpEX_M = this.ID_EX.get("pc");
			}
			
			System.out.println("**********");
			System.out.println("Executing:");
			System.out.println("pc from decoding: " + this.ID_EX.get("pc"));
			System.out.println("aluOP from decoding: " + this.ID_EX.get("aluOP"));
			System.out.println("regWriteSignal from decoding: " + this.ID_EX.get("regWriteSignal"));
			System.out.println("immediateMuxSignal from decoding: " + this.ID_EX.get("aluSRC"));
			System.out.println("memoryReadSignal from decoding: " + this.ID_EX.get("memRead"));
			System.out.println("memoryWriteSignal from decoding: " + this.ID_EX.get("memWrite"));
			System.out.println("memoryToRegisterSignal from decoding: " + this.ID_EX.get("memToReg"));
			System.out.println("shiftMuxOutput from decoding: " + this.ID_EX.get("shiftMuxOut"));
			System.out.println("branch from decoding: " + this.ID_EX.get("branch"));
			System.out.println("jump from decoding: " + this.ID_EX.get("jump"));
			System.out.println("readData1 from decoding: " + this.ID_EX.get("readData1"));
			System.out.println("readData2 from decoding: " + this.ID_EX.get("readData2"));
			System.out.println("writeReg from decoding: " + this.ID_EX.get("writeReg"));
			System.out.println("**********");
			this.executingFlag = true;
			int ForwardA = getForwardValueA(this.ID_EX.get("RSForward"),this.EX_M.get("writeReg"),this.EX_M.get("writeReg2"));
			int ForwardB = getForwardValueB(this.ID_EX.get("RTForward"),this.EX_M.get("writeReg"),this.EX_M.get("writeReg2"));
			int x = 0;
			int y = 0;
			if(ForwardA==0) {
				x=this.ID_EX.get("readData1");
			}
			else if (ForwardA==2) {
				x=this.EX_M.get("aluOut");

			}
			else if (ForwardA==1) {
				x=this.EX_M.get("aluOut2");

			}
			this.assignALUMux(this.ID_EX.get("readData2"), this.ID_EX.get("shiftMuxOut"), this.ID_EX.get("aluSRC"));


			if(ForwardB==0) {
				y=aluMux.getOutput();
			}
			else if (ForwardB==2) {
				y=this.EX_M.get("aluOut");

			}
			else if (ForwardB==1) {
				y=this.EX_M.get("aluOut2");

			}
			this.assignAlu(this.ID_EX.get("aluOP"),x,y);
			this.assignBranchMux(this.ID_EX.get("pc"), this.ID_EX.get("shiftMuxOut"), this.ID_EX.get("branch"));
			this.assignJumpMux(this.ID_EX.get("instruction"), this.ID_EX.get("pc"), this.ID_EX.get("jump"));
			this.EX_M.put("pc", this.ID_EX.get("pc"));
			
			this.EX_M.put("aluOut", this.alu.getOutput());
			this.EX_M.put("readData2", this.ID_EX.get("readData2"));
			this.EX_M.put("memRead", this.ID_EX.get("memRead"));
			this.EX_M.put("memWrite", this.ID_EX.get("memWrite"));
			this.EX_M.put("memToReg", this.ID_EX.get("memToReg"));
			this.EX_M.put("writeReg", this.ID_EX.get("writeReg"));
			this.EX_M.put("regWriteSignal", this.ID_EX.get("regWriteSignal"));
			this.EX_M.put("jump", this.ID_EX.get("jump"));
			this.EX_M.put("branch", this.ID_EX.get("branch") & (~(this.alu.getZero())));
			if (ID_EX.get("jump") == 1 || (ID_EX.get("branch") & (~(alu.getZero()))) == 1) {
				this.getPc().setData(this.jumpMux.getOutput());
				this.branchFlag = true;
			}
		}
	}

	public void mem() {
		System.out.println("**********");
		System.out.println("Memory:");
		System.out.println("pc from executing: " + this.EX_M.get("pc"));
		System.out.println("aluOutput from executing: " + this.EX_M.get("aluOut"));
		System.out.println("readData2 from executing: " + this.EX_M.get("readData2"));
		System.out.println("memoryRead from executing: " + this.EX_M.get("memRead"));
		System.out.println("memoryWrite from executing: " + this.EX_M.get("memWrite"));
		System.out.println("memoryToRegisterSignal from executing: " + this.EX_M.get("memToReg"));
		System.out.println("writeRegister from executing: " + this.EX_M.get("writeReg"));
		System.out.println("registerWriteSignal from executing: " + this.EX_M.get("regWriteSignal"));
		System.out.println("**********");
		this.memory.setAddress(this.EX_M.get("aluOut"));
		this.memory.setMemReadSignal(this.EX_M.get("memRead"));
		this.memory.setMemWriteSignal(this.EX_M.get("memWrite"));
		this.memory.setWriteData(this.EX_M.get("readData2"));
		if (this.memory.getMemWriteSignal() == 1) {
			this.memory.getMem()[this.memory.getAddress()] = this.memory.getWriteData();
			System.out.println(this.memory.getWriteData() + " has been written to cell " + this.memory.getAddress()
			+ " in memory");
		}
		if (this.memory.getMemReadSignal() == 1) {
			this.memory.setReadData(this.memory.getMem()[this.memory.getAddress()]);
		}
		this.M_WB.put("pc", this.EX_M.get("pc"));
		this.M_WB.put("writeReg", this.EX_M.get("writeReg"));
		this.M_WB.put("readData", this.memory.getReadData());
		this.M_WB.put("aluOut", this.EX_M.get("aluOut"));
		this.M_WB.put("memToReg", this.EX_M.get("memToReg"));
		this.M_WB.put("regWriteSignal", this.EX_M.get("regWriteSignal"));
		this.M_WB.put("jump", this.EX_M.get("jump"));
		this.M_WB.put("branch", this.EX_M.get("branch"));
		this.WB_Flag = true;
		this.Mem_Flag = false;
		this.testFlag = true;
	}

	public void writeBack() {
		System.out.println("**********");
		System.out.println("Write Back:");
		System.out.println("pc from memory: " + this.M_WB.get("pc"));
		System.out.println("memoryReadData from memory: " + this.M_WB.get("readData"));
		System.out.println("aluOutput from memory: " + this.M_WB.get("aluOut"));
		System.out.println("memoryToRegisterSignal from memory: " + this.M_WB.get("memToReg"));
		System.out.println("writeRegister from memory: " + this.M_WB.get("writeReg"));
		System.out.println("registerWriteSignal from memory: " + this.M_WB.get("regWriteSignal"));
		System.out.println("**********");
		this.assignWriteBackMux(this.M_WB.get("readData"), this.M_WB.get("aluOut"), this.M_WB.get("memToReg"));
		this.rFile.setWriteReg(this.M_WB.get("writeReg"));
		this.rFile.setRegWriteSignal(this.M_WB.get("regWriteSignal"));
		this.rFile.setWriteData(this.writeMux.getOutput());
		this.WB_Flag = false;
		this.M_WB.put("aluOut2", this.EX_M.get("aluOut"));
		this.M_WB.put("writeReg2", this.EX_M.get("writeReg"));

		if (this.branchFlag & (this.M_WB.get("jump") == 1 || this.M_WB.get("branch") == 1)) {
			this.branchFlag = false;
			this.ID_Flag = false;
			this.EX_Flag = false;
			this.Mem_Flag = false;
			this.WB_Flag = false;
			this.IF_Flag = true;
			this.decodeCounter = 0;
			this.executeCounter = 0;
			this.decodingFlag = false;
			this.executingFlag = false;
		}
	}

	public void assignReadMux(int R3, int R1) {
		this.readMux.setInput1(R3);
		this.readMux.setInput2(R1);
		this.readMux.setOutput(this.cUnit.getReadToReg());
	}

	public void assignShiftMux(int imm, int shamt) {
		this.shiftMux.setInput1(imm);
		this.shiftMux.setInput2(shamt);
		this.shiftMux.setOutput(this.cUnit.getShift());
	}

	public void assignALUMux(int data2, int shiftOut, int aluSRC) {
		this.aluMux.setInput1(data2);
		this.aluMux.setInput2(shiftOut);
		this.aluMux.setOutput(aluSRC);
	}

	public void assignBranchMux(int pc1, int shiftOut, int branch) {
		this.branchMux.setInput1(pc1);
		this.branchMux.setInput2(pc1 + shiftOut);
		this.branchMux.setOutput(branch & (~this.alu.getZero()));
	}

	public void assignJumpMux(int instruction, int pc1, int jump) {
		this.jumpMux.setInput1(this.branchMux.getOutput());
		int newPC = pc1 & 0b11110000000000000000000000000000;
		int temp1 = instruction & 0b00001111111111111111111111111111;
		int temp2 = (newPC | 0b00001111111111111111111111111111);
		int tempI = temp1 & temp2;
		this.jumpMux.setInput2(tempI);
		this.jumpMux.setOutput(jump);
	}

	public void assignAlu(int control,int A,int B) {
		this.alu.setInputA(A);
		this.alu.setInputB(B);
		this.alu.setControl(control);
		this.alu.setOutput(this.alu.getControl());
		this.alu.getOutput();
	}

	public void assignWriteBackMux(int readData, int aluOut, int memToReg) {
		this.writeMux.setInput1(readData);
		this.writeMux.setInput2(aluOut);
		this.writeMux.setOutput(memToReg);
	}


	public int getForwardValueA(int Id_Ex_rs,int Ex_Mem_Rd, int Mem_Wb_RD ) {
		if(this.EX_M.get("regWriteSignal")==1 & (Ex_Mem_Rd!=0)&(Id_Ex_rs==Ex_Mem_Rd)) {
			return 2;
		}
		else if(this.M_WB.get("regWriteSignal")==1 & (Mem_Wb_RD!=0)&(Id_Ex_rs==Mem_Wb_RD)) {

			return 1;
		}
		else {
			return 0;
		}

	}

	public int getForwardValueB(int Id_Ex_rt,int Ex_Mem_Rd, int Mem_Wb_RD ) {
		if(this.EX_M.get("regWriteSignal")==1 & (Ex_Mem_Rd!=0)&(Id_Ex_rt==Ex_Mem_Rd)) {
			return 2;
		}
		else if(this.M_WB.get("regWriteSignal")==1 & (Mem_Wb_RD!=0)&(Id_Ex_rt==Mem_Wb_RD)) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public void parse(String path) throws Exception {
		String line;
		Itype type = null;
		int inst = 0;
		try {
			File myFile = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(myFile));
			while ((line = reader.readLine()) != null) {
				String[] instruction = line.split(" ");
				String operation = instruction[0];
				boolean shiftFlag = false;
				switch (operation) {
				case "ADD":
					type = Itype.REGISTER;
					inst = 0;
					break;
				case "SUB":
					type = Itype.REGISTER;
					inst = 1;
					break;
				case "MULI":
					type = Itype.IMMEDIATE;
					inst = 2;
					break;
				case "ADDI":
					type = Itype.IMMEDIATE;
					inst = 3;
					break;
				case "BNE":
					type = Itype.IMMEDIATE;
					inst = 4;
					break;
				case "ANDI":
					type = Itype.IMMEDIATE;
					inst = 5;
					break;
				case "ORI":
					type = Itype.IMMEDIATE;
					inst = 6;
					break;
				case "J":
					type = Itype.JUMP;
					inst = 7;
					break;
				case "SLL":
					type = Itype.REGISTER;
					shiftFlag = true;
					inst = 8;
					break;
				case "SRL":
					type = Itype.REGISTER;
					shiftFlag = true;
					inst = 9;
					break;
				case "LW":
					type = Itype.IMMEDIATE;
					inst = 10;
					break;
				case "SW":
					type = Itype.IMMEDIATE;
					inst = 11;
					break;
				default:
					System.out.println("invalid instruction");
					return;
				}

				switch (type) {
				case REGISTER: {
					int R1 = Integer.parseInt(instruction[1].replace("R", " ").trim());
					int R2 = Integer.parseInt(instruction[2].replace("R", " ").trim());
					int R3 = Integer.parseInt(instruction[3].replace("R", " ").trim());
					inst = inst << 5;
					inst += R1;
					inst = inst << 5;
					inst += R2;
					inst = inst << 5;
					if (shiftFlag) {
						inst += 0;
						inst = inst << 13;
						inst += R3;
					} else {

						inst += R3;
						inst = inst << 13;
					}
					break;
				}
				case IMMEDIATE: {
					int R1 = Integer.parseInt(instruction[1].replace("R", " ").trim());
					int R2 = Integer.parseInt(instruction[2].replace("R", " ").trim());
					int R3 = Integer.parseInt(instruction[3].replace("R", " ").trim());
					inst = inst << 5;
					inst += R1;
					inst = inst << 5;
					inst += R2;
					inst = inst << 18;
					R3 = R3 & 0b00000000000000000111111111111111111;
					inst += R3;
					break;
				}
				case JUMP: {
					int R1 = Integer.parseInt(instruction[1].replace("R", " ").trim());
					inst = inst << 28;
					inst += R1;
					break;
				}
				}
				this.memory.getMem()[instructionPointer] = inst;
				instructionPointer++;

			}
			reader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}