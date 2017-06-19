/**
 * Created by Wilterson on 19/06/2017.
 */

package hotDogExpress.models.lists;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import hotDogExpress.models.SystemLog;

import java.util.List;

@XStreamAlias("logs")
public class SystemLogList{

    @XStreamImplicit(itemFieldName = "log")
    private List<SystemLog> logs;

    public List<SystemLog> getLogs() {
        return logs;
    }

    public void setLogs(List<SystemLog> logs) {
        this.logs = logs;
    }
}
