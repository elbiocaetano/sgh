package br.org.ccb.sgh.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.org.ccb.sgh.http.dto.SupportHouseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportHouse implements Serializable {
	private static final long serialVersionUID = 9062753348065537160L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String cnpj;
	@OneToOne(orphanRemoval = true, optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true)
	@Fetch(FetchMode.JOIN)
	private Address address;
	
	public static SupportHouse fromDto(Long id, Long addressId, SupportHouseDto supportHouseDto) {
		return SupportHouse.builder().id(id).name(supportHouseDto.getName()).cnpj(supportHouseDto.getCnpj())
				.address(Address.fromDto(addressId, supportHouseDto.getAddress())).build();
	}
}
