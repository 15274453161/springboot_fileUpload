package com.qgh.spring_mvc.moduels.bean;

import java.sql.Timestamp;
import java.util.List;

/** 
* @ClassName: OrgBean 
* @Description: 机构实体类
* @author liuwenfeng
* @date 2019年12月30日 上午10:00:39 
*  
*/
public class OrgBean extends BaseBean<OrgBean> {

	/** 机构Id */
	private Long orgId;

	/** 机构编码 */
	private Long orgCode;
	
	/** 机构名称 */
	private String name;
	
	/** 创建时间 */
	private Timestamp createTime;
	
	/** 修改时间 */
	private Timestamp updateTime;
	
	/** 父机构Id */
	private Long parentOrgId;
	
	/** 行政区域Id */
	private Long distinctId;

	/** 机构地址 */
	private String orgAddress;
	
	/** 联系方式 */
	private String orgPhone;
	
	/** 可用状态：1可用   0已弃用  */
	private String status = "1";
	
	/** 子机构集合 **/
	private List<OrgBean> children;

	/**补贴项目小类集合**/
	private List<String> subProjectList;



	public String getStatus() {
		return status;
	}

	public OrgBean setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public OrgBean setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
		return this;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public OrgBean setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
		return this;
	}

	public Long getOrgId() {
		return orgId;
	}

	public OrgBean setOrgId(Long orgId) {
		this.orgId = orgId;
		return this;
	}

	public Long getOrgCode() {
		return orgCode;
	}

	public OrgBean setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
		return this;
	}

	public String getName() {
		return name;
	}

	public OrgBean setName(String name) {
		this.name = name;
		return this;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public OrgBean setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		return this;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public OrgBean setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public OrgBean setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
		return this;
	}

	public Long getDistinctId() {
		return distinctId;
	}

	public OrgBean setDistinctId(Long distinctId) {
		this.distinctId = distinctId;
		return this;
	}

	public List<OrgBean> getChildren() {
		return children;
	}

	public void setChildren(List<OrgBean> children) {
		this.children = children;
	}

	public List<String> getSubProjectList() {
		return subProjectList;
	}

	public void setSubProjectList(List<String> subProjectList) {
		this.subProjectList = subProjectList;
	}


	@Override
	public String toString() {
		return "OrgBean{" +
				"orgId=" + orgId +
				", orgCode=" + orgCode +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", parentOrgId=" + parentOrgId +
				", distinctId=" + distinctId +
				", orgAddress='" + orgAddress + '\'' +
				", orgPhone='" + orgPhone + '\'' +
				", status='" + status + '\'' +
				", children=" + children +
				", subProjectList=" + subProjectList +
				'}';
	}
}

