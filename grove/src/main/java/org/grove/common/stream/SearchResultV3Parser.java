package org.grove.common.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultV3Parser {


    private static final String SECTION_CONSTANT = "const";
    private static final String SECTION_SPECIALKEY = "special";
    private static final String SECTION_ECHOKEY = "echo";
    private static final String SECTION_STATINFO = "stat";
    private static final String SECTION_DATA = "data";
    private static final String SECTION_DEBUG = "debug";
    private int returnNum;

    private Map<String, String> fieldsMap = null;


    public void parser(BufferParser parser){
        String sectionName;
        while (!parser.isEnd()) {
            sectionName = parser.getString();

            if (sectionName.length() == 0) {
                break;
            }

            if (SECTION_CONSTANT.equals(sectionName)) {
                fillConstSection(parser);
            } else if (SECTION_SPECIALKEY.equals(sectionName)) {
                fillSpecialKeySection(parser);
            } else if (SECTION_ECHOKEY.equals(sectionName)) {
                fillEchoKeySection(parser);
            } else if (SECTION_DEBUG.equals(sectionName)) {
                fillDebugSection(parser);
            } else if (SECTION_STATINFO.equals(sectionName)) {
                fillStatInfoSection(parser);
            } else if (SECTION_DATA.equals(sectionName)) {
                fillDataSection(parser);
            } else { // ���n���ֶ�
                System.out.println("sectionName:"+sectionName);
            }
        }
    }

    protected void fillDebugSection(BufferParser parser) {
        int size = parser.getInt();

        if (size > 10) {
            throw new NullPointerException("size > 10");
        }
        // ����ֶβ���5������辯��
        if (size != 5) {
            System.out.println("debugKey is more than 5");
        }

        String[] values = new String[size];

        for (int i = 0; i < size; i++) {
            values[i] = parser.getString();
        }

    }

    /**
     * ��䳣����
     * 
     * @param parser
     * @param sr
     * @param keyField
     */
    protected void fillConstSection(BufferParser parser){
        int size = parser.getInt();

        // Ŀǰ6��
        if (size < 6) {
        	 throw new NullPointerException("size < 6");
        }

        parser.getInt();
        parser.getInt();
        parser.getInt();
        returnNum = parser.getInt();
        parser.getString().equals("1");
        parser.getString().equals("1");

        if (size > 6) {
            parser.next(size - 6);
        }
    }

    /**
     * ���specialKey��
     * 
     * @param parser
     * @param sr
     * @param keyField
     */
    protected void fillSpecialKeySection(BufferParser parser){
        int size = parser.getInt();

        // ����5��
        if (size < 5) {
        	throw new NullPointerException("size < 5");
        }


        parser.getString();
        parser.getInt();
       parser.getInt();

        int synonymsCount = parser.getInt();
        int lenOfRelation = parser.getInt();

        if (size < (5 + synonymsCount + lenOfRelation)) {
            throw new NullPointerException(size + "<" + (5 + synonymsCount + lenOfRelation));
        }

        for (int i = 0; i < synonymsCount; i++) {
            parser.getString();
        }

        for (int i = 0; i < lenOfRelation; i++) {
            parser.getString();
        }

        size -= (5 + synonymsCount + lenOfRelation);

        if (size > 0) {
            parser.next(size);
        }
    }

    /**
     * ���EchoKey��
     * 
     * @param parser
     * @param sr
     * @param keyField
     */
    protected void fillEchoKeySection(BufferParser parser){
        int size = parser.getInt();

        // ����3��
        if (size < 3) {
            throw new NullPointerException(""+size);
        }

        parser.getInt();
        parser.getString();

        int numOfComb = parser.getInt();

        if (size < (3 + numOfComb)) {
            throw new NullPointerException(" :" + size + "<" + (3 + numOfComb));
        }

        for (int i = 0; i < numOfComb; i++) {
            String s = parser.getString();
            String[] arr = s.split(";");

            if (arr.length >= 2) {
            }
        }

        size -= (3 + numOfComb);

        if (size > 0) {
            parser.next(size);
        }
    }

    /**
     * ���StatInfo��
     * 
     * @param parser
     * @param sr
     * @param keyField
     */
    protected void fillStatInfoSection(BufferParser parser){
        int size = parser.getInt();
        int numOfStatInfos = parser.getInt();

        size--;

        for (int i = 0; (size > 0) && (i < numOfStatInfos); i++) {
            size--;

            int count = parser.getInt();
            for (int j = 0; (size > 0) && (j < count); j++) {

                size--;
               parser.getString();
            }
        }

        if (size > 0) {
            parser.next(size);
        }
    }

    /**
     * ���Data��
     * 
     * @param parser
     * @param sr
     * @param keyField
     */
    protected void fillDataSection(BufferParser parser){
        int size = parser.getInt();
        int fieldNum = parser.getInt();
        int aSize = 1 + (fieldNum * (returnNum + 1));

        if (size < aSize) {
            throw new NullPointerException("�ֶ������:" + size + "!=" + aSize);
        }

        // ȡField��
        List<String> filedIndex = new ArrayList<String>(100);

        for (int i = 0; i < fieldNum; i++) {
            filedIndex.add(parser.getString());
        }
        // ȡ���
        boolean isMap = false;
        if (returnNum > 1000) {
            returnNum = 1000;
           System.out.println("������󷵻ؼ�¼����[" + 1000 + "]����,break" );

        }

        for (int i = 0; i < returnNum; i++) {
            String fieldValue;

            for (int j = 0; j < fieldNum; j++) {
                fieldValue = parser.getString();
                //System.out.println(fieldValue);
            }

        }
        size -= aSize;

        if (size > 0) {
            parser.next(size);
        }
    }

}
