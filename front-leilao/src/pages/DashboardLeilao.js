import { useContext, useEffect } from 'react';
import { faker } from '@faker-js/faker';
// @mui
// import { useTheme } from '@mui/material/styles';
import { Grid, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';
// sections
import {
  AppNewsUpdate,
  AppOrderTimeline,
  AppWidgetSummary,
} from '../sections/@dashboard/app';
import LeilaoProcessContext from '../context/LeilaoProcessContext';

// ----------------------------------------------------------------------

export default function DashboardLeilao() {
  const { itensLeilao, eventos, leilaoAtivo, join } = useContext(LeilaoProcessContext)

  useEffect(() => console.log(eventos), [eventos])

  return (
    <Page title="Dashboard">
      <Container maxWidth="xl">
        <Typography variant="h4" sx={{ mb: 5 }}>
          Bem vindo ao leilão Online
        </Typography>

        <Grid container spacing={3}>
          {
            leilaoAtivo.map((item, index) => (
              <Grid key={index} item xs={12} sm={6} md={3}>
                <AppWidgetSummary clickEvent={() => join(item)} title="Weekly Sales" total={714000} icon={'ant-design:android-filled'} />
              </Grid>
            ))
          }
          {/* <Grid item xs={12} sm={6} md={3}>
            <AppWidgetSummary title="Weekly Sales" total={714000} icon={'ant-design:android-filled'} />
          </Grid>

          <Grid item xs={12} sm={6} md={3}>
            <AppWidgetSummary title="New Users" total={1352831} color="info" icon={'ant-design:apple-filled'} />
          </Grid>

          <Grid item xs={12} sm={6} md={3}>
            <AppWidgetSummary title="Item Orders" total={1723315} color="warning" icon={'ant-design:windows-filled'} />
          </Grid>

          <Grid item xs={12} sm={6} md={3}>
            <AppWidgetSummary title="Bug Reports" total={234} color="error" icon={'ant-design:bug-filled'} />
          </Grid> */}

          <Grid item xs={12} md={6} lg={8}>
            <AppNewsUpdate
              title="Ultimos itens registrados"
              list={itensLeilao.map((item, index) => ({
                id: item.id,
                title: `Raça ${faker.animal.cow()}`,
                description: `Valor sugerido ${item.valorSugerido}`,
                image: `/static/mock-images/covers/cover_${index + 1}.jpg`,
                postedAt: faker.date.recent(),
              }))}
            />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <AppOrderTimeline
              title="Ultimos Lances"
              list={eventos.map((_, index) => ({
                id: faker.datatype.uuid(),
                title: _,
                type: `order${index + 1}`,
                time: faker.date.past(),
              }))}
            />
          </Grid>

        </Grid>

      </Container>
    </Page>
  );
}
