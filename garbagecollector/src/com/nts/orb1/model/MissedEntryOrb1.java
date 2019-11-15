package com.nts.orb1.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "missed_entry_Orb1")
public class MissedEntryOrb1 {
	
	// *****comn vrible for evry entry**********
		@Id
		@GeneratedValue
		private int id;
		
		@Column(name = "missed_entry_type", length = 100)
		private String missed_entry_type;
		@Column(name = "missed_user_name", length = 50)
		private String missed_user_name;
		@Column(name = "missed_date", length = 30)
		private String missed_date;
		@Column(name = "missed_user_rank", length = 50)
		private String missed_user_rank;
		@Column(name = "source_entryid", length = 10)
		private int source_entryid;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMissed_entry_type() {
			return missed_entry_type;
		}
		public void setMissed_entry_type(String missed_entry_type) {
			this.missed_entry_type = missed_entry_type;
		}
		public String getMissed_user_name() {
			return missed_user_name;
		}
		public void setMissed_user_name(String missed_user_name) {
			this.missed_user_name = missed_user_name;
		}
		public String getMissed_date() {
			return missed_date;
		}
		public void setMissed_date(String missed_date) {
			this.missed_date = missed_date;
		}
		public String getMissed_user_rank() {
			return missed_user_rank;
		}
		public void setMissed_user_rank(String missed_user_rank) {
			this.missed_user_rank = missed_user_rank;
		}
		public int getSource_entryid() {
			return source_entryid;
		}
		public void setSource_entryid(int source_entryid) {
			this.source_entryid = source_entryid;
		}
		
		
	
}
