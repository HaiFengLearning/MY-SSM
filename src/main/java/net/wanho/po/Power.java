package net.wanho.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2019/8/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Power {
    private Integer powerId;
    private String powerName;
    private String status;
    private Integer parentId;
    private String url;
    private boolean isParent;

}
