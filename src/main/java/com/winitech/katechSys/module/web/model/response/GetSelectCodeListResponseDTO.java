package com.winitech.katechSys.module.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSelectCodeListResponseDTO {
	private String code;
	private String codenm;
}
