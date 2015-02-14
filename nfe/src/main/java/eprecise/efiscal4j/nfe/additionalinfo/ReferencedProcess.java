
package eprecise.efiscal4j.nfe.additionalinfo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import eprecise.efiscal4j.commons.utils.ValidationBuilder;
import eprecise.efiscal4j.nfe.types.NFeString;


/**
 * Grupo de informações do processo referenciado
 * 
 * @author Felipe Bueno
 * 
 */
public class ReferencedProcess implements Serializable {

	private static final long serialVersionUID = 1L;

	private @XmlElement(name = "nProc") @Size(min = 1, max = 60) @NFeString @NotNull final String processNumber;

	private @XmlElement(name = "indProc") @NotNull final ProcessOrigin processOrigin;

	public static class Builder {

		private String processNumber;

		private ProcessOrigin processOrigin;

		/**
		 * Indentificador do processo ou ato concessório
		 * 
		 * @param processNumber
		 * @return
		 */
		public Builder withProcessNumber(String processNumber) {
			this.processNumber = processNumber;
			return this;
		}

		/**
		 * @see ProcessOrigin
		 * @param processOrigin
		 * @return
		 */
		public Builder withProcessOrigin(ProcessOrigin processOrigin) {
			this.processOrigin = processOrigin;
			return this;
		}

		public ReferencedProcess build() {
			ReferencedProcess entity = new ReferencedProcess(this);
			ValidationBuilder.from(entity).validate().throwIfViolate();
			return entity;
		}

	}

	public ReferencedProcess() {
		this.processNumber = null;
		this.processOrigin = null;

	}

	public ReferencedProcess(Builder builder) {
		this.processNumber = builder.processNumber;
		this.processOrigin = builder.processOrigin;
	}

	public String getProcessNumber() {
		return this.processNumber;
	}

	public ProcessOrigin getProcessOrigin() {
		return this.processOrigin;
	}
}
