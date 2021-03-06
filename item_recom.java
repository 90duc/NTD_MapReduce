// ORM class for table 'item_recom'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sun Feb 04 04:40:39 PST 2018
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class item_recom extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Integer suid;
  public Integer get_suid() {
    return suid;
  }
  public void set_suid(Integer suid) {
    this.suid = suid;
  }
  public item_recom with_suid(Integer suid) {
    this.suid = suid;
    return this;
  }
  private Integer dpid;
  public Integer get_dpid() {
    return dpid;
  }
  public void set_dpid(Integer dpid) {
    this.dpid = dpid;
  }
  public item_recom with_dpid(Integer dpid) {
    this.dpid = dpid;
    return this;
  }
  private Double degree;
  public Double get_degree() {
    return degree;
  }
  public void set_degree(Double degree) {
    this.degree = degree;
  }
  public item_recom with_degree(Double degree) {
    this.degree = degree;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof item_recom)) {
      return false;
    }
    item_recom that = (item_recom) o;
    boolean equal = true;
    equal = equal && (this.suid == null ? that.suid == null : this.suid.equals(that.suid));
    equal = equal && (this.dpid == null ? that.dpid == null : this.dpid.equals(that.dpid));
    equal = equal && (this.degree == null ? that.degree == null : this.degree.equals(that.degree));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof item_recom)) {
      return false;
    }
    item_recom that = (item_recom) o;
    boolean equal = true;
    equal = equal && (this.suid == null ? that.suid == null : this.suid.equals(that.suid));
    equal = equal && (this.dpid == null ? that.dpid == null : this.dpid.equals(that.dpid));
    equal = equal && (this.degree == null ? that.degree == null : this.degree.equals(that.degree));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.suid = JdbcWritableBridge.readInteger(1, __dbResults);
    this.dpid = JdbcWritableBridge.readInteger(2, __dbResults);
    this.degree = JdbcWritableBridge.readDouble(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.suid = JdbcWritableBridge.readInteger(1, __dbResults);
    this.dpid = JdbcWritableBridge.readInteger(2, __dbResults);
    this.degree = JdbcWritableBridge.readDouble(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(suid, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dpid, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(degree, 3 + __off, 8, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(suid, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(dpid, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(degree, 3 + __off, 8, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.suid = null;
    } else {
    this.suid = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.dpid = null;
    } else {
    this.dpid = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.degree = null;
    } else {
    this.degree = Double.valueOf(__dataIn.readDouble());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.suid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.suid);
    }
    if (null == this.dpid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dpid);
    }
    if (null == this.degree) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.degree);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.suid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.suid);
    }
    if (null == this.dpid) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.dpid);
    }
    if (null == this.degree) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.degree);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(suid==null?"null":"" + suid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dpid==null?"null":"" + dpid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(degree==null?"null":"" + degree, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(suid==null?"null":"" + suid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dpid==null?"null":"" + dpid, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(degree==null?"null":"" + degree, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.suid = null; } else {
      this.suid = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dpid = null; } else {
      this.dpid = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.degree = null; } else {
      this.degree = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.suid = null; } else {
      this.suid = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dpid = null; } else {
      this.dpid = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.degree = null; } else {
      this.degree = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    item_recom o = (item_recom) super.clone();
    return o;
  }

  public void clone0(item_recom o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("suid", this.suid);
    __sqoop$field_map.put("dpid", this.dpid);
    __sqoop$field_map.put("degree", this.degree);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("suid", this.suid);
    __sqoop$field_map.put("dpid", this.dpid);
    __sqoop$field_map.put("degree", this.degree);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("suid".equals(__fieldName)) {
      this.suid = (Integer) __fieldVal;
    }
    else    if ("dpid".equals(__fieldName)) {
      this.dpid = (Integer) __fieldVal;
    }
    else    if ("degree".equals(__fieldName)) {
      this.degree = (Double) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("suid".equals(__fieldName)) {
      this.suid = (Integer) __fieldVal;
      return true;
    }
    else    if ("dpid".equals(__fieldName)) {
      this.dpid = (Integer) __fieldVal;
      return true;
    }
    else    if ("degree".equals(__fieldName)) {
      this.degree = (Double) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
