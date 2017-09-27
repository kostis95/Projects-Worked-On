package hva.nl.bloqs;

public class UartDataChunk {

    private int mOpcode;
    private byte[] mData;

    public UartDataChunk(int opcode, byte[] data) {
        mOpcode = opcode;
        mData = data;
    }

    public int getOpcode() {
        return mOpcode;
    }

    public byte[] getData() {
        return mData;
    }
}
