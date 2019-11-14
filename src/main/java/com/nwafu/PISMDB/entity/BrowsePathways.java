package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
public class BrowsePathways {
    Pic pic;
    List<Pictures>pictures;
    List<GroupFormat> compoundGroup;
    List<GroupFormat> proteinGroup;

}
