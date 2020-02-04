package com.ludo.forms;

public class LongueurForms {
	
		private Long idLongueur ;
		private String cotation ;
		private Long longueurLong ;
		private int nombrePoints ;
		
		public LongueurForms() {
			super();
			// TODO Auto-generated constructor stub
		}
		public LongueurForms(Long idLongueur, String cotation, Long longueurLong, int nombrePoints) {
			super();
			this.idLongueur = idLongueur;
			this.cotation = cotation;
			this.longueurLong = longueurLong;
			this.nombrePoints = nombrePoints;
		}
		public Long getIdLongueur() {
			return idLongueur;
		}
		public void setIdLongueur(Long idLongueur) {
			this.idLongueur = idLongueur;
		}
		public String getCotation() {
			return cotation;
		}
		public void setCotation(String cotation) {
			this.cotation = cotation;
		}
		public Long getLongueurLong() {
			return longueurLong;
		}
		public void setLongueurLong(Long longueurLong) {
			this.longueurLong = longueurLong;
		}
		public int getNombrePoints() {
			return nombrePoints;
		}
		public void setNombrePoints(int nombrePoints) {
			this.nombrePoints = nombrePoints;
		}
		
		
		
}
