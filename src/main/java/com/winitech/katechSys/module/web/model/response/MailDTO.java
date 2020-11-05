package com.winitech.katechSys.module.web.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDTO {
	private String address;
	private String title;
	private String message;

	private int mid;
	private String name;
}
