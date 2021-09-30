package cn.sevenlion.logistics.back.basic.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @author: qimeiwen
 * @create: 2021-09-30
 */
public abstract class BasicEntity implements Serializable {

    @TableId(value = "serial_code")
    private String serialCode;

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }
}
