package com.nbui.entity;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author EchoMao
 * @time 2019年1月11日
 * 
 */
@Component
@Scope(scopeName = "prototype")
public class PaperInfo implements Serializable {

    private static final long serialVersionUID = 8381884975828459838L;

    private Integer paperId;    //证件类型id
    private String paperType;   //证件类型名称

    public PaperInfo() {
        super();
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    @Override
    public String toString() {
        return "PaperInfo [paperId=" + paperId + ", paperType=" + paperType + "]";
    }

}
