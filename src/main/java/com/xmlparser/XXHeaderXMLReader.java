package com.xmlparser;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

public class XXHeaderXMLReader extends StreamReaderDelegate {

  public XXHeaderXMLReader(XMLStreamReader paramXMLStreamReader){
      super(paramXMLStreamReader);
  }

  @Override
  public String getAttributeNamespace(int paramInt)
  {
      return "";
  }

  @Override
  public String getNamespaceURI()
  {
      return "";
  }

}
